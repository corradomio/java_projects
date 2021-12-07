package org.hls.check;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import jext.serialization.ClassClosure;
import jext.serialization.fst.FstSerializer;
import jext.serialization.kryo.KryoSerializer;
import jext.serialization.protostuff.ProtostuffSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        System.out.println("Start parsing ...");
        ParseResult<CompilationUnit> pr =  new JavaParser().parse(new File(
            // "G:\\_Projects\\Projects.github\\apache_gitbox\\hbase\\hbase-thrift\\src\\main\\java\\org\\apache\\hadoop\\hbase\\thrift\\generated\\Hbase.java"
            // "G:\\_Projects\\Projects.github\\apache.gitbox\\commons-collections\\src\\main\\java\\org\\apache\\commons\\collections4\\bloomfilter\\ArrayCountingBloomFilter.java"
            // "D:\\SPLGroup\\Downloads\\AhmedWorkspace\\TestRepo\\storm\\storm-client\\src\\jvm\\org\\apache\\storm\\generated\\Nimbus.java"
            // "D:\\Projects.github\\other_projects\\hadoop\\hadoop-hdfs-project\\hadoop-hdfs\\src\\main\\java\\org\\apache\\hadoop\\hdfs\\server\\datanode\\DirectoryScanner.java"
            "D:\\Java\\Scala-2.10.7\\src\\src\\asm\\scala\\tools\\asm\\tree\\ParameterNode.java"
        ));
        if (!pr.isSuccessful()) {
            System.out.println(pr.getProblems());
            System.exit(1);
        }
        System.out.println("done!");

        CompilationUnit cu = pr.getResult().get();
        // System.out.println(cu);

        ClassClosure.of(cu);

        // FstSerializer.serialize(new File("java.fst"), cu);
        KryoSerializer.serialize(new File("java.kryo"), cu);
        ProtostuffSerializer.serialize(new File("java.proto"), cu);

        // cu = FstSerializer.deserialize(new File("java.fst"), CompilationUnit.class);
        cu = KryoSerializer.deserialize(new File("java.kryo"), CompilationUnit.class);
        cu = ProtostuffSerializer.deserialize(new File("java.proto"), CompilationUnit.class);
    }
}
