import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        /** ЗАДАНИЕ НА УСТАНОВКУ: */
// 1. В папке Games создайте несколько директорий: src, res, savegames, temp.
        File src = new File("C:\\Users\\Public\\Games", "src");
        File res = new File("C:\\Users\\Public\\Games", "res");
        File savegames = new File("C:\\Users\\Public\\Games", "savegames");
        File temp = new File("C:\\Users\\Public\\Games", "temp");
// 2. В каталоге src создайте две директории: main, test.
        File main = new File(src, "main");
        File test = new File(src, "test");
// 3. В подкаталоге main создайте два файла: Main.java, Utils.java.
        File mainJava = new File(main, "Main.java");
        File utilsJava = new File(main, "Utils.java");

// 4. В каталог res создайте три директории: drawables, vectors, icons.
        File drawables = new File(res, "drawables");
        File vectors = new File(res, "vectors");
        File icons = new File(res, "icons");
        File tempNote = new File("C:\\Users\\Public\\Games\\temp", "temp.txt");

        StringBuilder sb = new StringBuilder();
        if (src.mkdir()) sb.append("Файл src создан \n");
        if (res.mkdir()) sb.append("Файл res создан \n");
        if (savegames.mkdir()) sb.append("Файл savegames создан \n");
        if (temp.mkdir()) sb.append("Файл temp создан \n");
        if (main.mkdir()) sb.append("Файл main in src создан \n");
        if (test.mkdir()) sb.append("Файл test in src создан \n");
        if (mainJava.mkdir()) sb.append("Файл Main.java создан");
        if (utilsJava.mkdir()) sb.append("Файл Utils.java создан");
        if (drawables.mkdir()) sb.append("Файл drawables in res создан \n");
        if (vectors.mkdir()) sb.append("Файл vectors in res создан \n");
        if (icons.mkdir()) sb.append("Файл icons in res создан \n");
        String text = sb.toString();

        try {
            FileWriter sbTemp = new FileWriter(tempNote);
            sbTemp.write(text);
            sbTemp.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /** ЗАДАНИЕ НА СОХРАНЕНИЕ */
// 1. Создать три экземпляра класса GameProgress.
        GameProgress game1 = new GameProgress(78, 12, 19, 1900);
        GameProgress game2 = new GameProgress(56, 4, 5, 500);
        GameProgress game3 = new GameProgress(82, 8, 12, 1200);

// 2. Сохранить сериализованные объекты GameProgress в папку savegames из предыдущей задачи.
        File save1 = new File("C:\\Users\\Public\\Games\\savegames", "saveGame1.txt");
        File save2 = new File("C:\\Users\\Public\\Games\\savegames", "saveGame2.txt");
        File save3 = new File("C:\\Users\\Public\\Games\\savegames", "saveGame3.txt");

        saveGame(save1.getPath(), game1);
        saveGame(save2.getPath(), game2);
        saveGame(save3.getPath(), game3);

// 3. Созданные файлы сохранений из папки savegames запаковать в архив zip.
        String[] saveArray = new String[3];
        saveArray[0] = save1.getPath();
        saveArray[1] = save2.getPath();
        saveArray[2] = save3.getPath();

        zipFiles("C:\\Users\\Public\\Games\\savegames", saveArray);


// 4. Удалить файлы сохранений, лежащие вне архива.
        if (save1.delete()) System.out.println("save 1 deleted");
        if (save2.delete()) System.out.println("save 2 deleted");
        if (save3.delete()) System.out.println("save 3 deleted");

    }


    public static void saveGame(String pathname, GameProgress game) {
        try {
            FileOutputStream fos = new FileOutputStream(pathname);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            System.out.println("Game saved");
            oos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String pathOfZip, String[] pathOfObj) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathOfZip))) {
            for (int i = 0; i < pathOfObj.length; i++) {
                FileInputStream fis = new FileInputStream(pathOfObj[i]);
                ZipEntry entry = new ZipEntry("packed_" + pathOfObj[i]);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                System.out.println("file delivered to zip");
                zout.closeEntry();
                fis.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
