package br.com.recyclerview.business

import br.com.recyclerview.model.HeaderDataModel

class GetHeaderModelUseCase {

    operator fun invoke(): List<HeaderDataModel> {
        return listOf(
            HeaderDataModel("Suspensão"),
            HeaderDataModel("Oleo e Filtro"),
            HeaderDataModel("Eletricos"),
            HeaderDataModel("Mecânicos"),
            HeaderDataModel("Acessórios")
        )
    }
}