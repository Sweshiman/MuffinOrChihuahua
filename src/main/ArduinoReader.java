package src.main;

import com.fazecast.jSerialComm.SerialPort;

/**
 * Created by alexanderkarlsson on 06/10/2017.
 */
public class ArduinoReader extends Thread {
    String buffer = "";

    public void run() {
        SerialPort comPort = null;
        for (SerialPort sp : SerialPort.getCommPorts()) {
            if (sp.getDescriptivePortName().contains("ino")) {//If not genuine Arduino, change this
                comPort = sp;
            }
        }

        if (comPort == null) {
            System.out.println("No Arduino found");
            System.exit(0);
        }

        comPort.openPort();
        try {
            while (true) {
                //READ DATA
                while (comPort.bytesAvailable() == 0) {
                    Thread.sleep(5);
                }

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                buffer += new String(readBuffer, "UTF-8");
                //HANDLE DATA
                handleBuffer(comPort);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comPort.closePort();
    }

    private void handleBuffer(SerialPort comPort) {
        while (!buffer.isEmpty()) {
            //SPLIT TO SEE IF THERE IS ANY FULL COMMANDS IN LINE
            if(buffer.contains("\r\n")){
                String[] split = buffer.split("\r\n");
                //SAVE CHECK IF LAST CHAR IS AN ENDCHAR
                boolean lastCharEnd = (buffer.charAt(buffer.length()-1)=='\n');

                //HANDLE ANY FULL LINES APART FROM LAST
                for(int i = 0; i < split.length - 1; i++){
                    handleCommand(split[i], comPort);
                }

                //RESET BUFFER TO LAST ELEMENT IN ARRAY IF NOT NULL
                buffer = split.length > 0 ? split[split.length-1] : "";
                //HANDLE LAST PIECE IF THE LAST CHAR WAS NEWLINE
                if(lastCharEnd){
                    handleCommand(buffer, comPort);
                    buffer="";
                }else{
                    break;//Break loop, buffer is not complete
                }
            }
        }
    }

    private void handleCommand(String command, SerialPort comPort){
        //HANDLE THE COMMAND
    }
}
