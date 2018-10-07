package gabrielmarcos.com.br.helpieblog.models

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
class UsersModel(val id: Int,
                 val name: String,
                 val email: String,
                 val company: CompanyModel,
                 val address: CityModel)