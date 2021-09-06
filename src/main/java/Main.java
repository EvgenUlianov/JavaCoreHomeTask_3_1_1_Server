import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задача \"Клиент-серверное приложение\" (server)");

        int port = 9533;

        Deque<String> kids = new ArrayDeque<>();
        Deque<String> adults = new ArrayDeque<>();


        while (true){

            // правильно ли я использую try с ресурсами?
            try (ServerSocket serverSocket = new ServerSocket(port))  {

                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                out.println("Write your name");
                //System.out.println("New connection accepted: ");
                String name = in.readLine();
                out.println(String.format("Hi %s, Are you child? (yes/no)", name));

                String answer = in.readLine();
                if (answer.equalsIgnoreCase("yes")) {
                    out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                    kids.add(name);
                } else {
                    out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                    adults.add(name);
                }

                out.println("Complete? (yes/no)");
                answer = in.readLine();
                if (answer.equalsIgnoreCase("yes")) {
                    out.println("kids: " + kids.toString());
                    out.println("adults: " + adults.toString());
                }



                if (answer.equalsIgnoreCase("shuthodnw")) {
                    System.out.println("Service has shut down");
                    break;
                }


            } catch (IOException ex){
                System.out.println(ex);
                System.out.println("Service has shut down");
                break;
            }



        }

    }

}
