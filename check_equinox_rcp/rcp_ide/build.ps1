param(
    [string]$EclipseHome = 'D:\Projects.github\java_projects\check_equinox_rcp\eclipse.run',
    [string]$JavaHome = 'D:\Java\Jdk17.0',
    [switch]$Run
)
$ErrorActionPreference = 'Stop'
$plugins = Join-Path $EclipseHome 'plugins'
$javac = Join-Path $JavaHome 'bin\javac.exe'
if (!(Test-Path $plugins)) { throw "Eclipse plugins directory not found: $plugins" }
if (!(Test-Path $javac)) { throw "JDK compiler not found: $javac" }
$build = Join-Path $PSScriptRoot 'build'
$classes = Join-Path $build 'classes'
New-Item -ItemType Directory -Force $classes | Out-Null
$sources = @(Get-ChildItem (Join-Path $PSScriptRoot 'plugin\src') -Recurse -Filter '*.java' | ForEach-Object FullName)
& $javac --release 17 -encoding UTF-8 -sourcepath (Join-Path $PSScriptRoot 'plugin\src') -classpath "$plugins\*" -d $classes @sources
if ($LASTEXITCODE -ne 0) { throw 'Compilation failed.' }
$bundle = Join-Path $build 'rcp.ide_1.0.0.jar'
& (Join-Path $JavaHome 'bin\jar.exe') cfm $bundle (Join-Path $PSScriptRoot 'META-INF\MANIFEST.MF') -C $classes rcp -C $PSScriptRoot plugin.xml
if ($LASTEXITCODE -ne 0) { throw 'Bundle packaging failed.' }
Write-Output "Built $bundle"
if ($Run) {
    $configuration = Join-Path $build 'configuration'
    New-Item -ItemType Directory -Force $configuration | Out-Null
    $ids = [System.Collections.Generic.HashSet[string]]::new()
    [xml]$product = Get-Content (Join-Path $PSScriptRoot 'rcp_ide.product')
    foreach ($plugin in $product.product.plugins.plugin) { [void]$ids.Add($plugin.id) }
    $available = @(Get-ChildItem $plugins | Where-Object { $_.PSIsContainer -or $_.Extension -eq '.jar' })
    foreach ($id in $ids) {
        if ($id -ne 'rcp.ide' -and !($available | Where-Object { ($_.Name -split '_\d+\.')[0] -eq $id })) {
            throw "Product dependency missing from target platform: $id"
        }
    }
    $bundles = @($available | Where-Object {
        $ids.Contains(($_.Name -split '_\d+\.')[0]) -and $_.Name -notmatch '^org\.eclipse\.osgi_|^org\.eclipse\.equinox\.launcher'
    } | ForEach-Object {
        $bundlePath = if ($_.PSIsContainer) { $_.FullName + '\' } else { $_.FullName }
        $uri = ([System.Uri]$bundlePath).AbsoluteUri
        $level = if ($_.Name -match '^org\.eclipse\.equinox\.(common|event)_|^org\.apache\.felix\.scr_') { '@2:start' }
                 elseif ($_.Name -match '^org\.eclipse\.core\.runtime_') { '@4:start' } else { '@4' }
        "reference:$uri$level"
    })
    $bundles += "reference:$(([System.Uri]$bundle).AbsoluteUri)"
    $framework = Get-ChildItem $plugins -Filter 'org.eclipse.osgi_*.jar' | Select-Object -First 1
    $launcher = Get-ChildItem $plugins -Filter 'org.eclipse.equinox.launcher_*.jar' | Select-Object -First 1
    if (!$framework -or !$launcher) { throw 'Eclipse framework or launcher missing.' }
    @(
        "osgi.bundles=$($bundles -join ',')"
        'osgi.bundles.defaultStartLevel=4'
        "osgi.framework=$(([System.Uri]$framework.FullName).AbsoluteUri)"
        'eclipse.application=rcp.ide.application'
        'eclipse.product=rcp.ide.product'
    ) | Set-Content (Join-Path $configuration 'config.ini') -Encoding ASCII
    & (Join-Path $JavaHome 'bin\java.exe') -jar $launcher.FullName -clean -configuration $configuration -data (Join-Path $PSScriptRoot 'runtime-workspace') -consoleLog
    if ($LASTEXITCODE -ne 0) { throw "Application exited with code $LASTEXITCODE" }
}
