
mvn -f shape_editor/pom.xml dependency:copy-dependencies@copy-libs-for-eclipse


Quando Equinox dice che una class non e' presente MA e' presente il jar,
vuol dire che in qualche file MANIFEST.MF non e' stata fatta l'esportazione del
namespace

------------------------------------------------------
- XML file types
------------------------------------------------------

    CableLayer/CableLayer               D:\Projects.ebtic\project.ftth.data\EbticDATA\.AlAinMMR1-clean\Cables.xml
    CableLayer/RingCableLayer           D:\Projects.ebtic\project.ftth.data\EbticDATA\.Gitex2021\Cables.xml
    CableLayer/ribbonParameters         D:\Projects.ebtic\project.ftth.data\EbticDATA\Taawun\2022\BaniYas_North_P1\.Baniyas_North_AutoBranchingTest\Cables.xml
    CableLayer/DiagramParameters        D:\Projects.ebtic\project.ftth.data\EbticDATA\Taawun\2022\BaniYas_North_P1\.Baniyas_North_AutoBranchingTest\Cables.xml
    CableLayer/RingParameters           D:\Projects.ebtic\project.ftth.data\EbticDATA\Taawun\2022\Yeef\.Yeef_09_02_1R\Cables.xml
    CableLayer/Boundary                 D:\Projects.ebtic\project.ftth.data\EbticDATA\Taawun\old\.Taawun-Ain-1_mediumV2\Cables.xml

    XMLnetwork/Network                  D:\Projects.ebtic\project.ftth.data\EbticDATA\.AlAinMMR1-clean\Civils.xml
    XMLnetwork/PlanningResults          D:\Projects.ebtic\project.ftth.data\EbticDATA\Etisalat\existing network\From _Naser\FDH-1535\Output-NoRC\cableDesign.xml

    SpanLinks/SpanLink                  D:\Projects.ebtic\project.ftth.data\EbticDATA\Etisalat\Greenfield\FDH12\fdh12-span.xml
    SpliceNodes/Splice                  D:\Projects.ebtic\project.ftth.data\EbticDATA\Etisalat\Greenfield\FDH12\fdh12-splice.xml
    StructureNodes/Node                 D:\Projects.ebtic\project.ftth.data\EbticDATA\Etisalat\Greenfield\FDH12\fdh12-structure.xml
    TransmediaLinks/TransmediaLink      D:\Projects.ebtic\project.ftth.data\EbticDATA\Etisalat\Greenfield\FDH12\fdh12-transmedia.xml

    PlanningResultsTaawunStar/FDH       D:\Projects.ebtic\project.ftth.data\EbticDATA\Taawun\old\.Taawun-Ain-1_mediumCivilsOnly3BDR\Cables.xml

    clusters/parameters                 D:\Projects.ebtic\project.ftth.data\EbticDATA\Etisalat\Greenfield\FromDeveloper\SHS6\catchmentareas.xml
    clusters/networkinfo

    Saves/Save                          D:\Projects.ebtic\project.ftth.data\EbticDATA\Etisalat\Greenfield\HashimIssue\FDH-134\FDH134pro.xml

    INVENTORY/STRUCTURE                 D:\Projects.ebtic\project.ftth.data\EbticDATA\Etisalat\Greenfield\HashimIssue\RasGhurab\output\July2020\structureRasGhurabV2Final.xml
    INVENTORY/SPAN
    INVENTORY/SPAN_SPAN_UNIT
    INVENTORY/SPAN_UNIT
    INVENTORY/TRANSMEDIA                D:\Projects.ebtic\project.ftth.data\EbticDATA\Etisalat\Greenfield\NorthernEmirates\FromAjeesh\trans.xml
    INVENTORY/EQUIPMENT                 D:\Projects.ebtic\project.ftth.data\EbticDATA\MasterPlan\export\equipment-demo.xml
    INVENTORY/SPLICE_CLOSURE            D:\Projects.ebtic\project.ftth.data\EbticDATA\MasterPlan\Latest\export\splices.xml

    SPLICES/ODF                         D:\Projects.ebtic\project.ftth.data\EbticDATA\Taawun\2024\Dubai\DubaiHills\Export\Cable1\connectivity_DH1.xml
    SPLICES/Splice_ID
    SPLICES/MINIODFS
    SPLICES/DC                          D:\Projects.ebtic\project.ftth.data\EbticDATA\Taawun\2025\NE-Integration\4B CABLE 12.06.12.15\conn.xml
    SPLICES/OBT

    PlanningResultsTaawunStar/FDH       D:\Projects.ebtic\project.ftth.data\EbticDATA\Taawun\old\.Taawun-Ain-1_mediumCivilsOnly3BDR\Cables.xml

------------------------------------------------------
- CableLayer
------------------------------------------------------
I nodi di CableLayer hanno un tipo


<CableLayer>
    <CableLayer>
    ...
</CableLayer>

<CableLayer>
    <Boundary ID="N5668" NetworkID="N5668" Type="Polygon"
        Boundary="(368387.899140315,2661760.8758341833);(368275.9598187833,2662035.0460564857);(367563.7661644001,2661744.652744106);
                  (367716.2632111245,2661384.5001443955);(367996.92266945774,2661502.9287019577);(367996.92266945774,2661502.9287019577);
                  (367996.92266945774,2661502.9287019577);(368387.899140315,2661760.8758341833);"
        CentrePoint="(367940.6391744677,2661716.586477166)"
        TotalBox="0"
        TotalEB="124"
        TotalJoint="0"
        TotalRJ="94"
        TotalStructurePlot="124"
    />
    <Node ID="N132" X="367459.24028965"   Y="2661774.5455747913"                      Type="Exchange"/>
    <Node ID="N247" X="367966.1090662195" Y="2661614.0160620124" Connection_Nb="1524" Type="MMR">
        <ParentNode ID="N132" X="367459.24028965" Y="2661774.5455747913"              Type="Exchange"/>
        <pathToParent Length="697.6483154296875">
            <Link ID="E2690"/>
            <Link ID="E1301"/>
            <Link ID="E1092"/>
            <Link ID="E1995"/>
            <Link ID="E2408"/>
            <Link ID="E1702"/>
            <Link ID="E1593"/>
            <Link ID="E1096"/>
            <Link ID="E2935"/>
            <Link ID="E1291"/>
            <Link ID="E2267"/>
            <Link ID="E1617"/>
            <Link ID="E3308"/>
        </pathToParent>
    </Node>
</CableLayer>


<RingCableLayer Version="Taawun" Tool="EBTIC(etisalat)">
    <RingNode>
        <Node ID="N617" X="262300.44033321296" Y="2685825.3954417263" Type="MMR"/>
        ...
    </RingNode>
    <CommercialBox>
        <Node ID="N14" X="262639.8427970451" Y="2685667.7879149173" Type="EB"/>
        ...
    </CommercialBox>
    <RingLinks>
        <Link ID="E113" SX="262464.9172740625" SY="2685661.3897147537" EX="262539.67494925286" EY="2685624.2003662903"/>
        ...
    </RingLinks>
</RingCableLayer>


------------------------------------------------------
- XMLnetwork
------------------------------------------------------

<XMLnetwork Format="EBTIC_ETISALAT XMLnetwork Format" Name="unavailable" description="civilLayer">
    <Network Bounds="(367004.06955674133,369306.28,2660442.018923245,2662688.8365865913)" ID="">
        ...
    </Network>
    ...
</XMLnetwork>

<Network Bounds="(367004.06955674133,369306.28,2660442.018923245,2662688.8365865913)" ID="">
    <Node ID="" Type="structurePlot" X="368845.6647949219" Y="2662032.335876465" ebticX="0.0" ebticY="0.0" ebticID="N2819"
          attributesAndValues="shp.FID_EB_1*1871*shp.OBJECT_COD*BLD*shp.TYPE*EB*shp.ORIG_FID*935"/>
    ...
    <Link LENGTH="95.5645704372869" Type="D56" WAYPOINTS="(368717.4048081434,2661430.8173439745);" EbticStartID="N2554" EbticEndID="N2600" EbticID="E1299"/>
    ...
</Network>

