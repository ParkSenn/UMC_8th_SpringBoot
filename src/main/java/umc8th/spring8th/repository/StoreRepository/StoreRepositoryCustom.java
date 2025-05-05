package umc8th.spring8th.repository.StoreRepository;

import umc8th.spring8th.domain.Store;

import java.util.List;

public interface StoreRepositoryCustom {

    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}
