# Caderneta Digital 📋
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/im2back/Voll.med/blob/main/LICENSE)  
# Sobre o projeto
Esta API foi concebida com o objetivo de simplificar o processo de registro das compras realizadas pelos clientes em pequenos estabelecimentos comerciais, como mercados de bairro. Nos pequenos mercados, é comum adotar a prática de permitir que os clientes comprem "fiado" e efetuem o pagamento no final do mês. No entanto, a organização e integridade das anotações podem ser comprometidas à medida que o número de clientes e o volume de transações aumentam. Em suma esse é um sistema simples, feito afim de substituir as cadernetas de papel. No momento o desenvolvimento é 100% autoral, ainda não tem contribuições de terceiros.

## Layout da tela  principal da aplicação
![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/89dc57ba-341d-42dd-bc28-905cf8427dae)


## Layout da tela de cadastro de clientes
![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/e700abf4-d263-4ee3-82a8-f8b7f17ec59d)

![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/4a67c4ba-8f5d-42fa-ad77-6675cd94f0f1)



## Layout da tela de listar todos os clientes (somente informações importantes)
![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/5952328a-71bf-4a50-9509-f6236b72cdae)

<br>

## Detalhamento do cliente (todas as informações + Ações)

![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/fb7520e4-aaef-4986-a5ee-eaab447e5901)

<br>

## Layout da tela de estatisticas

![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/d9134151-854f-407f-9806-a8c9b021291e)


<br>

## PLANTA UML do pacote model
![UML](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/ec1564e4-132f-464b-abd6-77816825fecd)

<br>

## Segurança com Spring-security (JWT)
![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/7d77aae2-9a70-421d-80b5-ef1fe4d0cbc7)

![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/0e561a26-74c9-4981-99bd-19e577f0bcf4)

### Senhas encriptadas com o Bcrypt

![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/1eda6ebe-de9c-4710-938f-773e003be4ef)



## Futuras implementações (Atualização de 05/12/2023)


( 1 ) ------> Implementar o Spring-Security JWT
Obs : O security foi implementado porém o Token está sendo enviado como parametro. O token deve ser enviado por medio de um cabeçalho.


( 2 ) -------> Acrecentar um Status da conta (PAGO || EM ABERTO) (AJUSTAR RETORNO DAS ESTATISTICAS)


( 4 ) -------> Adicionar campo de pesquisa por nome do cliente ,na pagina onde listamos todos os clientes


( 5 ) -------> Implementar sistema de black list (TOKEN)

# Tecnologias utilizadas
## Back end
- Linguagem : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Java-blue.svg?style=flat&logo=coffeescript&logoColor=white" target="_blank"></a> <br>
- Framework : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/SpringBoot-white.svg?style=flat&logo=springboot&logoColor=green" target="_blank"></a> <br>
- Persistência de dados : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/JPA-Hibernate-darkgreen.svg?style=flat&logo=hibernate&logoColor=white" target="_blank"></a> <br>
- Gerenciador de dependencias : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Maven-white.svg?style=flat&logo=apachemaven&logoColor=darkgreen" target="_blank"></a> <br>
- DataBase de testes : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/H2-DataBase-darkblue.svg?style=flat&logo=h2&logoColor=blue" target="_blank"></a> <br>
- DataBase de produção : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/MySQL-blue.svg?style=flat&logo=mysql&logoColor=white" target="_blank"></a>
- Thymeleaf(Front-end) : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Thymeleaf-white.svg?style=flat&logo=Thymeleaf&logoColor=red" target="_blank"></a> <br>
- HTML(Front-end) : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/HTML-white.svg?style=flat&logo=HTML5&logoColor=red" target="_blank"></a> <br>
- CSS(Front-end) : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/css-white.svg?style=flat&logo=css3&logoColor=darkblue" target="_blank"></a> <br>
- Ferramenta para testar requisições : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/PostMan-white.svg?style=flat&logo=postman&logoColor=red" target="_blank"></a> <br>
- Versionamento do repositorio : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/GitHub-white.svg?style=flat&logo=github&logoColor=black" target="_blank"></a> <br>
- Versionamento do banco de dados : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/FlyWay-white.svg?style=flat&logo=flyway&logoColor=red" target="_blank"></a> <br>
- Segurança da API : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/SpringSecurity-white.svg?style=flat&logo=springsecurity&logoColor=green" target="_blank"></a>





# Como executar o projeto

## Back end
Pré-requisitos: Java 17 , Mysql database versão 8.0.31 CE

```bash
# clonar repositório
# entrar na pasta do projeto back end
cd backend
# executar o projeto
./mvnw spring-boot:run
```
O sistema precisa de um usuário e senha para isso basta executar o seguinte comando: 👉
mysql -u root -pRtyfghvbn1* -D mercearia_api -e "INSERT INTO usuarios (login, senha, role) VALUES ('admin', 'admin', 'admin');"
Esse comando criará um usuario : admin e senha: admin
# Autor

Jefferson Richards Sena de souza
https://www.linkedin.com/in/jefferson-richards-sena-de-souza-4110a3222/
