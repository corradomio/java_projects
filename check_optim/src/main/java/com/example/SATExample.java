package com.example;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.specs.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class SATExample {
    public static void main(String[] args) {

        args = new String[]{
            "D:\\Projects.github\\java_projects\\check_optim\\dmacs\\DIMACS Benchmark Instances\\hanoi\\hanoi5.cnf"
        };

        ISolver solver = SolverFactory.newDefault();
        solver.setTimeout(3600); // 1 hour timeout
        DimacsReader reader = new DimacsReader(solver);
        PrintWriter out = new PrintWriter(System.out,true);
        // CNF filename is given on the command line
        try {
            IProblem problem = reader.parseInstance(args[0]);
            if (problem.isSatisfiable()) {
                System.out.println("Satisfiable !");
                reader.decode(problem.model(), out);
                out.flush();
            } else {
                System.out.println("Unsatisfiable !");
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
        } catch (ParseFormatException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        } catch (ContradictionException e) {
            System.out.println("Unsatisfiable (trivial)!");
        } catch (TimeoutException e) {
            System.out.println("Timeout, sorry!");
        }
    }
}
