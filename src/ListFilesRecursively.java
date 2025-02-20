import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListFilesRecursively {
    public static void main(String[] args) {
        ArrayList<File> fileList = new ArrayList<>();
        String[] extend = {
                ".jpg", ".png", ".gif",
                "main.py", ".app"
        };
        String[] path = {
                "/",
        };

        // передаем несколько путей
        for (String paths : path) {
            getFiles(new File(paths), fileList, extend);
        }

        for (File file : fileList) {
            System.out.println("*".repeat(25));
            String result = "<a href=\"file:///" + file.getAbsolutePath() + "\"</a>";
            System.out.println(result);

            System.out.println(file.getAbsolutePath());
        }
    }

    private static void getFiles(File rootFile, List<File> fileList, String[] extend) {
        if (rootFile.isDirectory()) {
            System.out.println("searching: " + rootFile.getAbsolutePath());
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        getFiles(file, fileList, extend);
                    } else {
                        if (hasValidExtension(file, extend)) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }

    private static boolean hasValidExtension(File file, String[] extend) {
        String fileName = file.getName().toLowerCase();
        for (String ext : extend) {
            if (fileName.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}
