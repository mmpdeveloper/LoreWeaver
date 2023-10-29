package com.example.loreweaverai;


import java.io.*;
import java.util.Scanner;

public class PythonCaller {

    public String callPythonScript(String prompt) {
        String result = "";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("/Users/mariahp/IdeaProjects/LoreWeaverAi/venv/bin/python3", "textGenerator.py");

            processBuilder.directory(new File("/Users/mariahp/IdeaProjects/LoreWeaverAi/python"));

            Process process = processBuilder.start();

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                writer.write(prompt);
                writer.flush();
            }


            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result += line + "\n";
                }
            }

            int exitCode = process.waitFor();
            System.out.println("Python script exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your prompt:");
        String userPrompt = scanner.nextLine();

        PythonCaller caller = new PythonCaller();
        String result = caller.callPythonScript(userPrompt);

        System.out.println("Result from Python:\n" + result);
    }
}
