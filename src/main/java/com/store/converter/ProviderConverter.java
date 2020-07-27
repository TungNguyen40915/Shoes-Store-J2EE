package com.store.converter;

import com.store.dto.ProviderDTO;
import com.store.model.Provider;

import java.util.ArrayList;
import java.util.List;

public class ProviderConverter {
    public static ProviderDTO ConvertProviderEntityToProviderDTO(Provider provider){
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setId(provider.getId());
        providerDTO.setName(provider.getName());

        return providerDTO;
    }

    public static List<ProviderDTO> ConvertProviderEntityToProviderDTO(List<Provider> providerList){
        List<ProviderDTO> result = new ArrayList<ProviderDTO>();
        for (Provider item: providerList) {
            result.add(ConvertProviderEntityToProviderDTO(item));
        }
        return result;
    }
}
