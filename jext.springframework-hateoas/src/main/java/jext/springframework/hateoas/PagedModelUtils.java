package jext.springframework.hateoas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


public class PagedModelUtils {

    public static <D> PagedModel<D> withSelfRel(PagedModel<D> paged, Class<?> controllerClass, Object... parameters) {
        paged.add(
            linkTo(controllerClass, parameters).withSelfRel()
        );
        return paged;
    }

    public static <T, D extends RepresentationModel<?>> PagedModel<D>
    toPagedModel(Page<T> page, RepresentationModelAssembler<T, D> assembler) {
        List<D> list = toList(page, assembler);
        PagedModel.PageMetadata metadata = metadataOf(page);

        PagedModel<D> paged = PagedModel.of(list, metadata);

        return paged;
    }

    public static <T, D extends RepresentationModel<?>> PagedModel<D>
    toPagedModel(Set<T> content, Pageable pageable, RepresentationModelAssembler<T, D> assembler) {
        return toPagedModel(new ArrayList<>(content), pageable, assembler);
    }

    public static <T, D extends RepresentationModel<?>> PagedModel<D>
    toPagedModel(List<T> content, Pageable pageable, RepresentationModelAssembler<T, D> assembler) {
        List<D> wrapped = selectWrapped(content, pageable, assembler);
        PagedModel.PageMetadata metadata = metadataOf(content, pageable);
        return PagedModel.of(wrapped, metadata);
    }

    // public static <T, D extends RepresentationModel<?>> Page<D> toPage(List<T> content, Pageable pageable, RepresentationModelAssembler<T, D> assembler) {
    //     List<D> wrapped = selectWrapped(content, pageable, assembler);
    //     return new PageImpl<D>(wrapped, pageable, content.size());
    // }

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

    private static <T, D extends RepresentationModel<?>> List<D> selectWrapped(List<T> content, Pageable pageable, RepresentationModelAssembler<T, D> assembler) {
        List<T> selected = selectContent(content, pageable);

        return selected.stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
    }

    private static <T, D extends RepresentationModel<?>> List<D> toList(Page<T> page, RepresentationModelAssembler<T, D> assembler) {
        List<D> list = new ArrayList<>();
        for(T entity : page)
            list.add(assembler.toModel(entity));
        return list;
    }

    private static <T> PagedModel.PageMetadata metadataOf(List<T> content, Pageable pageable) {
        long size = pageable.getPageSize();
        long number = pageable.getPageNumber();
        long totalElements =  content.size();
        long totalPages = (long) Math.ceil((0.+totalElements)/(0.+size));
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(size, number, totalElements, totalPages);
        return metadata;
    }

    private static <T> PagedModel.PageMetadata metadataOf(Page<T> page) {
        long size = page.getSize();
        long number = page.getNumber();
        long totalElements =  page.getTotalElements();
        long totalPages = page.getTotalPages();
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(size, number, totalElements, totalPages);
        return metadata;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

}
