using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

class Program
{
    static async Task Main()
    {
        TcpListener server = new TcpListener(IPAddress.Any, 5000);
        server.Start();
        Console.WriteLine("Server started on port 5000...");

        while (true)
        {
            TcpClient client = await server.AcceptTcpClientAsync();
            _ = HandleClient(client);
        }
    }

    static async Task HandleClient(TcpClient client)
    {
        using (client)
        {
            NetworkStream stream = client.GetStream();
            byte[] buffer = new byte[1024];
            int bytesRead = await stream.ReadAsync(buffer, 0, buffer.Length);
            string request = Encoding.UTF8.GetString(buffer, 0, bytesRead);
            
            Console.WriteLine($"Received request:\n{request}");

            // Формируем HTTP-ответ
            string response = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nContent-Length: 13\r\n\r\nHello, world!";
            byte[] responseData = Encoding.UTF8.GetBytes(response);

            await stream.WriteAsync(responseData, 0, responseData.Length);
        }
    }
}
