import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4000)) {
            System.out.println("Server is running and waiting for connections...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String clientInput = in.readLine();
                    String result = processRequest(clientInput);
                    out.println(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String processRequest(String inputText) {
        // Додайте код для обробки запиту та генерації відповіді
        // Наприклад, розділіть рядок, знайдіть найбільше і найменше число, і сформуйте відповідь.
        String[] numbers = inputText.split("\\s+");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (String number : numbers) {
            int currentNumber = Integer.parseInt(number);
            max = Math.max(max, currentNumber);
            min = Math.min(min, currentNumber);
        }

        return max + " " + min;
    }
}

