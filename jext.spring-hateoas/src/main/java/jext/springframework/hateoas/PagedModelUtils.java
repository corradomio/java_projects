package jext.springframework.hateoas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class PagedModelUtils {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Select the PageMetadata with the highest totalElements
     *
     * @param md1 first metadata
     * @param md2 second metadata
     * @return the selected metadata
     */
    public static PagedModel.PageMetadata max(PagedModel.PageMetadata md1, PagedModel.PageMetadata md2) {
        if (md1.getTotalElements() > md2.getTotalElements())
            return md1;
        else
            return md2;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Add the 'self' link to the 'PagedModel'
     *
     * @param paged PagedModel to update
     * @param controllerClass class of the controller
     * @param parameters parameters used to compose the link
     * @return the 'self' link
     * @param <D> type of the object 'model'
     */
    public static <D> PagedModel<D> withSelfRel(PagedModel<D> paged, Class<?> controllerClass, Object... parameters) {
        paged.add(
            linkTo(controllerClass, parameters).withSelfRel()
        );
        return paged;
    }

    public static <T, D extends RepresentationModel<?>> PagedModel<D>
    toPagedModel(Set<T> content, Pageable pageable, RepresentationModelAssembler<T, D> assembler) {
        return toPagedModel(new ArrayList<>(content), pageable, assembler);
    }

    public static <T, D extends RepresentationModel<?>> PagedModel<D>
    toPagedModel(List<T> content, Pageable pageable, RepresentationModelAssembler<T, D> assembler) {
        List<D> wrapped = selectWrapped(content, pageable, assembler);
        PagedModel.PageMetadata metadata = toPageMetadata(content.size(), pageable);
        return PagedModel.of(wrapped, metadata);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Compose a Page with all elements in 'content'
     *
     * @param content elements to aggregate
     * @return the Page wrapper
     * @param <T> type of the objects in the collection
     */
    public static <T> Page<T> toPage(List<T> content) {
        Pageable pageable = jext.springframework.data.domain.PageRequest.of(0, content.size());
        return toPage(content, pageable);
    }

    /**
     * Compose a Page with the elements in 'content' selected in 'pageable'
     *
     * @param content elements to aggregate
     * @param pageable page to select
     * @return the Page wrapper
     * @param <T> Type of the object in the collection
     */
    public static <T> Page<T> toPage(List<T> content, Pageable pageable) {
        List<T> selected = selectContent(content, pageable);
        return new PageImpl<>(selected, pageable, content.size());
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------
    //
    //  pages starts from 0!
    //

    /**
     * Select the elements from 'content' based on the content of 'pageable'
     *
     * @param content elements to select
     * @param pageable page to select
     * @return list of selected objects
     * @param <T> type of the objects in the collection
     */
    private static <T> List<T> selectContent(List<T> content, Pageable pageable) {
        int size = content.size();
        int fromIndex = (int) pageable.getOffset();
        int toIndex = Math.min(fromIndex + pageable.getPageSize(), content.size());

        if (fromIndex < 0) fromIndex = 0;
        if (fromIndex > size) fromIndex = 0;
        if (toIndex < 0) toIndex = 0;
        if (toIndex > size) toIndex = size;
        if (toIndex < fromIndex) { int t = toIndex; toIndex = fromIndex; fromIndex = t; }

        return content.subList(fromIndex, toIndex);
    }

    /**
     * As 'selectContent' but the selected elements are converted in a 'model' using 'assemble'
     * @param content elements to select
     * @param pageable page to select
     * @param assembler used to convert each element in the correspondent 'model'
     * @return list of wrapped selected objects
     * @param <T> type of the objects in the collection
     * @param <D> type of the object 'model'
     */
    private static <T, D extends RepresentationModel<?>> List<D> selectWrapped(
            List<T> content,
            Pageable pageable,
            RepresentationModelAssembler<T, D> assembler)
    {
        List<T> selected = selectContent(content, pageable);

        return selected.stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static PagedModel.PageMetadata toPageMetadata(long totalElements, Pageable pageable) {
        long size = pageable.getPageSize();
        long number = pageable.getPageNumber();
        long totalPages = (long) Math.ceil((0.+totalElements)/(0.+size));
        return new PagedModel.PageMetadata(size, number, totalElements, totalPages);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
