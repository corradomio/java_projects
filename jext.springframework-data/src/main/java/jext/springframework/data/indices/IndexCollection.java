package jext.springframework.data.indices;

import java.util.ArrayList;

public class IndexCollection extends ArrayList<IndexDescriptor> {

    public boolean containsIndex(String name) {
        for(IndexDescriptor desc : this)
            if (desc.name.equals(name))
                return true;
        return false;
    }
}
