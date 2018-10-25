package com.ixdproject.madam.backend;

import com.fazecast.jSerialComm.SerialPort;
import com.ixdproject.madam.ui.views.MuffinView;

/**
 * Created by alexanderkarlsson on 06/10/2017.
 */
public class ArduinoReader extends Thread {
    private String buffer = "";
    private SerialPort comPort = null;
    private MuffinView view;

    public ArduinoReader(MuffinView view) {
        super();
        this.view = view;
    }

    public void changeView(MuffinView view) {
        this.view = view;
    }

    public void write(byte[] msg) {
        comPort.writeBytes(msg, 1);
    }

    public void run() {
        for (SerialPort sp : SerialPort.getCommPorts()) {
            if (sp.getDescriptivePortName().contains("IOUSBHostDevice") && !sp.getDescriptivePortName().contains("Dial")) {//If not genuine Arduino Uno, change this
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

                try {
                    byte[] readBuffer = new byte[comPort.bytesAvailable()];
                    int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                    buffer += new String(readBuffer, "UTF-8");
                    //HANDLE DATA
                    handleBuffer(comPort);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comPort.closePort();
    }

    private void handleBuffer(SerialPort comPort) {
        while (!buffer.isEmpty()) {
            //SPLIT TO SEE IF THERE IS ANY FULL COMMANDS IN LINE
            if (buffer.contains("\r\n")) {
                String[] split = buffer.split("\r\n");
                //SAVE CHECK IF LAST CHAR IS AN ENDCHAR
                boolean lastCharEnd = (buffer.charAt(buffer.length() - 1) == '\n');

                //HANDLE ANY FULL LINES APART FROM LAST
                for (int i = 0; i < split.length - 1; i++) {
                    handleCommand(split[i], comPort);
                }

                //RESET BUFFER TO LAST ELEMENT IN ARRAY IF NOT NULL
                buffer = split.length > 0 ? split[split.length - 1] : "";
                //HANDLE LAST PIECE IF THE LAST CHAR WAS NEWLINE
                if (lastCharEnd) {
                    handleCommand(buffer, comPort);
                    buffer = "";
                } else {
                    break;//Break loop, buffer is not complete
                }
            } else {
                break;//Break loop, incomplete buffer
            }
        }
    }

    private void handleCommand(String command, SerialPort comPort) {
        view.handleInput(command);
    }
}
