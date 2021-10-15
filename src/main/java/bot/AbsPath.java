package bot;

import java.nio.file.FileSystems;
import java.nio.file.Path;

class AbsPath {
    private Path path = FileSystems.getDefault().getPath("").toAbsolutePath();

    String getAbsPath(String path){
        return this.path.toString() + "/" + path;
    }
}
