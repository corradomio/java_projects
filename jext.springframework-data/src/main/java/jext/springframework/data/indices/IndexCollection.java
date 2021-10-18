package jext.springframework.data.indices;

import java.util.ArrayList;

public class IndexCollection extends ArrayList<IndexDescriptor> {

    /**
     * Check if there is an index with the specified name.
     *
     * There is a problem with the name of the standard indices (created with "CREATE_INDEX ON :label(propery)"):
     * the index 'name' is "index_{counter}" or "Unnamed index"
     * This means that the index name is not unique.
     * The alternative, to understand if an index is already defined, is to use ":label(property)".
     * In this case, it is necessary to REMOVE all spaces
     *
     * @param name index name OR ":label(property)"
     * @return if the index is present
     */
    public boolean containsIndex(String name) {
        name = trim(name);
        for(IndexDescriptor desc : this)
            // check
            // 1) for name OR
            // 2) for description REMOVING all spaces
            if (desc.name.equals(name) || trim(desc.description).contains(name))
                return true;
        return false;
    }

    private static String trim(String s) {
        while (s.contains(" "))
            s = s.replace(" ", "");
        return s;
    }
}
