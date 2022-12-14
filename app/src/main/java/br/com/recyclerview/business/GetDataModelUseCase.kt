package br.com.recyclerview.business

import br.com.recyclerview.model.DataModel
import br.com.recyclerview.model.HeaderDataModel
import br.com.recyclerview.model.ItemDataModel

class GetDataModelUseCase {

    operator fun invoke(): List<DataModel> {
        return listOf(
            HeaderDataModel("Suspensão"),
            ItemDataModel("Alinhamento"),
            ItemDataModel("Amortecedor"),
            ItemDataModel("Pneus"),
            HeaderDataModel("Oleo e Filtro"),
            ItemDataModel("Oleo"),
            ItemDataModel("Filtro"),
            HeaderDataModel("Eletricos"),
            ItemDataModel("Bateria"),
            ItemDataModel("Elétrica"),
            HeaderDataModel("Mecânicos"),
            ItemDataModel("Injeção eletrônica"),
            ItemDataModel("Ignição"),
            ItemDataModel("Freios"),
            ItemDataModel("Direção"),
            ItemDataModel("Correias de motor"),
            ItemDataModel("Cabos"),
            HeaderDataModel("Acessórios"),
            ItemDataModel("Palheta de limpador"),
            ItemDataModel("Extintor de incêndio"),
            ItemDataModel("Ar-condicionado (assepsia)")
        )
    }
}