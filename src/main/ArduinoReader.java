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
                while (comPort.bytesAvailable() == 0) {
                    Thread.sleep(20);
                }

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                buffer += new String(readBuffer, "UTF-8");
                handleBuffer(comPort);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comPort.closePort();
    }

    private void handleBuffer(SerialPort comPort) {
        while (!buffer.isEmpty()) {
            /*String id = buffer.substring(0, buffer.indexOf("\r\n"));
            DataHandler.addHit(Integer.parseInt(id));
            buffer = buffer.substring(buffer.indexOf("\r\n") + 2);*/
            //buffer = "";
            if(buffer.contains("\r\n")){
                String[] split = buffer.split("\r\n");
                boolean lastCharEnd = (buffer.charAt(buffer.length()-1)=='\n');
                for(int i = 0; i < split.length - 1; i++){
                    handleCommand(split[i], comPort);
                }
                buffer = split.length > 0 ? split[split.length-1] : "";
                if(lastCharEnd){
                    handleCommand(buffer, comPort);
                    buffer="";
                }
            }
            /*System.out.println(buffer);
            if (Integer.parseInt(buffer) < 100) {
                comPort.writeBytes("1".getBytes(), "1".getBytes().length);
            } else {
                comPort.writeBytes("0".getBytes(), "0".getBytes().length);
            }
            buffer = "";*/
        }
    }

    private void handleCommand(String command, SerialPort comPort){
        comPort.writeBytes(command.getBytes(), command.getBytes().length);
    }
}
