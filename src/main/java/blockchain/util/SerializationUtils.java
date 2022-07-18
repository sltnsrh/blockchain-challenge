package blockchain.util;

import blockchain.model.BlockChain;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public final class SerializationUtils {

    public static void serialize(Object obj, String fileName) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)
        ) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException("Can't write object " + obj
            + " to the file " + fileName, e);
        }
    }

    public static Object deserialize(String fileName) {
        try (
                FileInputStream fileInputStream = new FileInputStream(fileName);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)
        ) {
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Can't read from the file: " + fileName + ". New blockchain was created. \n");
            return new BlockChain(new ArrayList<>(), new ArrayList<>());
        }
    }
}
