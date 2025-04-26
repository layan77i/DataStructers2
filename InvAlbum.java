package coding;

public class InvAlbum {
    private String name;
    private String condition;
    private InvIndexPhotoManager invmanager;
    private int NbComps;

    // Constructor
    public InvAlbum(String name, String condition, InvIndexPhotoManager manager)
    {
        this.name = name;
        this.condition = condition;
        this.invmanager = manager;
        NbComps =0 ;
    }
    
    // Return the name of the album
    public String getName()
    {
        return name;
    }
    
    // Return the condition associated with the album
    public String getCondition()
    {
        return condition;
    }

    // Return the manager
    public InvIndexPhotoManager getManager()
    {
        return invmanager;
    }
    
    // Return all photos that satisfy the album condition
    public LinkedList<Photo> getPhotos()
    {
        BST<LinkedList<Photo>> photosBST = invmanager.getPhotos();
        LinkedList<Photo> Rphotos = new LinkedList<Photo>();
        NbComps =0 ;
        String [] tags;
        
        if (this.condition.compareTo("") != 0)
            tags = condition.split(" AND ");
        else
            tags = photosBST.inOrder().split(" AND ");

        for ( int i = 0 ; i < tags.length ; i++)
        {
            
            if ( photosBST.findkey(tags[i]) == true)
            {
                if (i == 0)
                {
                    LinkedList<Photo > miniTag = photosBST.retrieve();
                    miniTag.findFirst();
                    while ( ! miniTag.last())
                    {
                        Rphotos.insert(miniTag.retrieve());
                        miniTag.findNext();
                    }
                    Rphotos.insert(miniTag.retrieve());
                }
                else
                {
                    if (condition.compareToIgnoreCase("") != 0 )
                         Rphotos  = Function1 ( Rphotos , photosBST.retrieve());
                    else
                        Rphotos  = Function2 ( Rphotos , photosBST.retrieve());    
                }
            }
            else
            {
                Rphotos = new LinkedList<Photo>();
                break;
            }
        }
        return Rphotos;
    }
   
    // Return the number of tag comparisons used to find all photos of the album
    public int getNbComps()
    {
        return NbComps;
    }

    private LinkedList<Photo> Function1 ( LinkedList<Photo> list1 ,LinkedList<Photo> list2)
    {
        LinkedList<Photo> result = new LinkedList<Photo>();
        
        if (! list2.empty())
        {
            list2.findFirst();
            while (! list2.last())
            {
                if (! list1.empty())
                {
                    boolean found = false;
                    list1.findFirst();
                    while (! list1.last() && ! found)
                    {
                        if (list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                        {
                            NbComps++;
                            found = true;
                        }
                        list1.findNext();
                    }
                    if (! found && list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                    {
                        NbComps++;
                        found = true;
                    }
                    if (found )
                        result.insert(list2.retrieve());
                        
                }
                list2.findNext();
            }
            
            boolean found = false;
            list1.findFirst();
            while (! list1.last() && ! found)
            {
                if (list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                {
                    NbComps++;
                    found = true;
                }
                list1.findNext();
            }
            if (! found && list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
            {
                NbComps++;
                found = true;
            }
            if (found )
                result.insert(list2.retrieve());
                              
        }
        return result;
    }
    private LinkedList<Photo> Function2 ( LinkedList<Photo> list1 ,LinkedList<Photo> list2)
    {
        if (! list2.empty())
        {
            list2.findFirst();
            while (! list2.last())
            {
                if (! list1.empty())
                {
                    boolean found = false;
                    list1.findFirst();
                    while (! list1.last() && ! found)
                    {
                        if (list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                        {
                            NbComps++;
                            found = true;
                        }
                        list1.findNext();
                    }
                    if (! found && list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                    {
                        NbComps++;
                        found = true;
                    }
                    if (!found )
                        list1.insert(list2.retrieve());
                        
                }
                list2.findNext();
            }
            
            boolean found = false;
            list1.findFirst();
            while (! list1.last() && ! found)
            {
                if (list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                {
                    NbComps++;
                    found = true;
                }
                list1.findNext();
            }
            if (! found && list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
            {
                NbComps++;
                found = true;
            }
            if (!found )
                list1.insert(list2.retrieve());
                              
        }
        return list1;
    }

    
   
}