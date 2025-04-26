package coding;

public class PhotoManager {

    LinkedList<Photo> photoList;

  
    public PhotoManager() {
        photoList = new LinkedList<Photo>();
    }

   
    public void addPhoto(Photo P) {
        if (!isPhotoExists(P.getPath(), photoList)) {
            photoList.insert(P);
        }
    }

  
    private boolean isPhotoExists(String path, LinkedList<Photo> list) {
        if (list.empty()) {
            return false;
        }

        list.findFirst();
        while (!list.last()) {
            if (list.retrieve().getPath().equalsIgnoreCase(path)) {
                return true;
            }
            list.findNext();
        }

      
        return list.retrieve().getPath().equalsIgnoreCase(path);
    }


    public void deletePhoto(String path) {
        if (!isPhotoExists(path, photoList)) {
            return;
        }

        if (!photoList.empty()) {
            photoList.findFirst();
            boolean removed = false;

            while (!photoList.last() && !removed) {
                if (photoList.retrieve().getPath().equalsIgnoreCase(path)) {
                    photoList.remove();
                    removed = true;
                } else {
                    photoList.findNext();
                }
            }

           
            if (!removed && photoList.retrieve().getPath().equalsIgnoreCase(path)) {
                photoList.remove();
            }
        }
    }

  
    public LinkedList<Photo> getPhotos() {
        return photoList;
    }
}
