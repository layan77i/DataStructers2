package coding;

public class InvIndexPhotoManager {
    BST<LinkedList<Photo>> Inverted_Index;
    
    // Constructor
    public InvIndexPhotoManager()
    {
        Inverted_Index = new BST<LinkedList<Photo>>();
    }
    
    // Add a photo
    public void addPhoto(Photo p)
    {
        LinkedList<String> tags = p.getTags();
        if (! tags.empty())
        {
            tags.findFirst();
            while (!tags.last())
            {
                if ( Inverted_Index.findkey(tags.retrieve()) == true )
                {
                    LinkedList<Photo> photos_inverted = Inverted_Index.retrieve();
                    photos_inverted.insert(p);
                    Inverted_Index.update(tags.retrieve(), photos_inverted);
                }
                else
                {
                    LinkedList<Photo> photos_inverted = new LinkedList<Photo>();
                    photos_inverted.insert(p);
                    Inverted_Index.insert(tags.retrieve(), photos_inverted);
                }
                tags.findNext();
            }
            if ( Inverted_Index.findkey(tags.retrieve()) == true )
            {
                LinkedList<Photo> photos_inverted = Inverted_Index.retrieve();
                photos_inverted.insert(p);
                Inverted_Index.update(tags.retrieve(), photos_inverted);
            }
            else
            {
                LinkedList<Photo> photos_inverted = new LinkedList<Photo>();
                photos_inverted.insert(p);
                Inverted_Index.insert(tags.retrieve(), photos_inverted);
            }
        }
    }
    
    // Delete a photo
    public void deletePhoto(String path)
    {
        String AllTags = Inverted_Index.inOrder();
        String[] tags = AllTags.split(" AND ");
        
        for ( int i = 0; i < tags.length ; i++)
        {
            Inverted_Index.findkey(tags[i]);
           LinkedList<Photo> photos_inverted = Inverted_Index.retrieve();
           photos_inverted.findFirst();
           while ( ! photos_inverted.last())
           {
               if ( photos_inverted.retrieve().getPath().compareToIgnoreCase(path) == 0)
               {
                   photos_inverted.remove();
                   break;
               }
               else
                  photos_inverted.findNext();
               
           }   
           if (photos_inverted.retrieve().getPath().compareToIgnoreCase(path) == 0)
                photos_inverted.remove();
           
           if ( photos_inverted.getSize() == 0)
               Inverted_Index.removeKey(tags[i]);
           else
               Inverted_Index.update(tags[i], photos_inverted);
        }
    }
    
    // Return the inverted index of all managed photos
    public BST<LinkedList<Photo>> getPhotos()
    {
        return Inverted_Index;
    }
}