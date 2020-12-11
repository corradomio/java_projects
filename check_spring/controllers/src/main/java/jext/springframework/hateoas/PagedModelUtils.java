package jext.springframework.hateoas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PagedModelUtils {

    public static <T, D extends RepresentationModel<?>> PagedModel<D>
    toPagedModel(Page<T> page, RepresentationModelAssembler<T, D> assembler) {
        List<D> list = toList(page, assembler);
        PagedModel.PageMetadata metadata = metadataOf(page);
        return PagedModel.of(list, metadata);
    }

    public static <T, D extends RepresentationModel<?>> PagedModel<D>
    toPagedModel(List<T> content, Pageable pageable, RepresentationModelAssembler<T, D> assembler) {
        List<D> wrapped = selectWrapped(content, pageable, assembler);
        PagedModel.PageMetadata metadata = metadataOf(content, pageable);
        return PagedModel.of(wrapped, metadata);
    }

    public static <T, D extends RepresentationModel<?>> Page<D>
    toPage(List<T> content, Pageable pageable, RepresentationModelAssembler<T, D> assembler) {
        List<D> wrapped = selectWrapped(content, pageable, assembler);
        return new PageImpl<D>(wrapped, pageable, content.size());
    }

    public static <T> Page<T> selectPage(List<T> content, Pageable pageable) {
        List<T> selected = selectContent(content, pageable);
        return new PageImpl<>(selected, pageable, content.size());
    }

    public static <T> List<T> selectContent(List<T> content, Pageable pageable) {
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

    public static <T, D extends RepresentationModel<?>> List<D>
    selectWrapped(List<T> content, Pageable pageable, RepresentationModelAssembler<T, D> assembler) {
        List<T> selected = selectContent(content, pageable);

        return selected.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    public static <T, D extends RepresentationModel<?>> List<D>
    toList(Page<T> page, RepresentationModelAssembler<T, D> assembler) {
        List<D> list = new ArrayList<>();
        for(T entity : page)
            list.add(assembler.toModel(entity));
        return list;
    }

    /*
        pages starts from 0!
     */
    public static <T> PagedModel.PageMetadata metadataOf(List<T> content, Pageable pageable) {
        long size = pageable.getPageSize();
        long totalElements =  content.size();
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
                size,
                pageable.getPageNumber(),
                content.size(),
                (long) Math.ceil((0.+totalElements)/(0.+size))
        );
        return metadata;
    }

    public static <T> PagedModel.PageMetadata metadataOf(Page<T> page) {
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
                page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        );
        return metadata;
    }

}
