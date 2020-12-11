package jext.springframework.data.domain;

public class Pageable extends PageRequest {

    public static org.springframework.data.domain.Pageable of(int page, int size) {
        return new Pageable(page, size);
    }

    public Pageable() {

    }

    public Pageable(int p, int s) {
        super(p, s);
    }

    @Override
    protected org.springframework.data.domain.Pageable pageOf(int page, int size) {
        return new Pageable(page, size);
    }
}
