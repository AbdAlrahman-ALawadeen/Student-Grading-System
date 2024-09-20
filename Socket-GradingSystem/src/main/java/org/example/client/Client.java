package org.example.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static DataInputStream inputFromServer;
    private static DataOutputStream outputToServer;
    private static Scanner scanner;

    public void start() {
        try {
            Socket socket = new Socket("localhost", 5647);
            inputFromServer = new DataInputStream(socket.getInputStream());
            outputToServer = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            while (true) {
                try {
                    String line = inputFromServer.readUTF();
                    if (line.equals("download")) {
                        showProgressBar();
                        return;
                    }
                    System.out.println(line);

                    while (inputFromServer.available() != 0) {
                        line = inputFromServer.readUTF();
                        if (line.equals("download")) {
                            showProgressBar();
                            return;
                        }
                        System.out.println(line);
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }

                String sendToServer = scanner.nextLine();
                outputToServer.writeUTF(sendToServer);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private static void showProgressBar() {
        StringBuilder progressBar = new StringBuilder();

        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(100);

                progressBar.setLength(0);

                int percentage = (i * 100) / 10;

                progressBar.append("█".repeat(i));

                progressBar.append("░".repeat(Math.max(0, 10 - i)));

                progressBar.append(" ").append(percentage).append("%");

                System.out.print("\r" + progressBar);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print("\r");
        System.out.print(" ".repeat(10 + 10));
        System.out.println("\nDownload complete!");
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
