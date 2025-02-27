const net = require("net");

const client = new net.Socket();
client.connect(5000, "localhost", () => {
    console.log("Connected to server");
    client.write("GET / HTTP/1.1\r\nHost: localhost\r\n\r\n");
});

client.on("data", (data) => {
    console.log("Received response:\n" + data.toString());
    client.destroy();
});

client.on("close", () => {
    console.log("Connection closed");
});
