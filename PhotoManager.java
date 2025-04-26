package coding;

public class PhotoManager {

    LinkedList<Photo> photoList;

    // Constructor
    public PhotoManager() {
        photoList = new LinkedList<Photo>();
    }

    // Method to add a photo
    public void addPhoto(Photo newPhoto) {
        if (!isPhotoExists(newPhoto.getPath(), photoList)) {
            photoList.insert(newPhoto);
        }
    }

    // Helper method to check if a photo exists
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

        // Final check for the last item
        return list.retrieve().getPath().equalsIgnoreCase(path);
    }

    // Method to delete a photo by path
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

            // Check the last item if not already removed
            if (!removed && photoList.retrieve().getPath().equalsIgnoreCase(path)) {
                photoList.remove();
            }
        }
    }

    // Return the list of photos
    public LinkedList<Photo> getPhotos() {
        return photoList;
    }
}