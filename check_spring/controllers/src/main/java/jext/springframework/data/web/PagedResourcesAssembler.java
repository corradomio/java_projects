package jext.springframework.data.web;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;
import java.util.stream.Collectors;


public class PagedResourcesAssembler<U, T extends RepresentationModel<? extends T>>
        implements RepresentationModelAssembler<Page<U>, PagedModel<T>> {

    private RepresentationModelAssembler<U, T> modelAssembler;

    public PagedResourcesAssembler(RepresentationModelAssembler<U, T> modelAssembler) {
        this.modelAssembler = modelAssembler;
    }

    public PagedModel<T> toModel(Page<U> page, Link... links) {
        List<T> content = this.map(page.getContent());
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements());
        return PagedModel.of(content, metadata, links);
    }

    @Override
    public PagedModel<T> toModel(Page<U> page) {
        List<T> content = this.map(page.getContent());
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements());
        return PagedModel.of(content, metadata);
    }

    private List<T> map(List<U> content) {
        return content.stream().map(entity -> modelAssembler.toModel(entity)).collect(Collectors.toList());
    }

}
