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

        saveGame("C:\\Users\\Public\\Games\\savegames\\saveGame1.txt", game1);
        saveGame("C:\\Users\\Public\\Games\\savegames\\saveGame2.txt", game2);
        saveGame("C:\\Users\\Public\\Games\\savegames\\saveGame3.txt", game3);

// 3. Созданные файлы сохранений из папки savegames запаковать в архив zip.
        File saveZIP = new File("C:\\Users\\Public\\Games\\savegames", "saving.zip");
        if (saveZIP.isDirectory()) System.out.println("saveZIP added");

        zipFiles("C:\\Users\\Public\\Games\\savegames\\saving.zip",
                "C:\\Users\\Public\\Games\\savegames\\saveGame1.txt");

        zipFiles("C:\\Users\\Public\\Games\\savegames\\saving.zip",
                "C:\\Users\\Public\\Games\\savegames\\saveGame2.txt");

        zipFiles("C:\\Users\\Public\\Games\\savegames\\saving.zip",
                "C:\\Users\\Public\\Games\\savegames\\saveGame3.txt");

// 4. Удалить файлы сохранений, лежащие вне архива.
        if(save1.delete()) System.out.println("save 1 deleted");;
        save2.delete();
        save3.delete();

    }



    public static void saveGame(String pathname, GameProgress game){
        try{
            FileOutputStream fos = new FileOutputStream(pathname);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String pathOfZip, String pathOfObj){
        try{
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathOfObj));
            FileInputStream fis = new FileInputStream(pathOfZip);
            ZipEntry entry = new ZipEntry(pathOfObj);
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}