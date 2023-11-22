# Caderneta Digital 📋
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/im2back/Voll.med/blob/main/LICENSE)  
# Sobre o projeto
Esta API foi concebida com o objetivo de simplificar o processo de registro das compras realizadas pelos clientes em pequenos estabelecimentos comerciais, como mercados de bairro. Nos pequenos mercados, é comum adotar a prática de permitir que os clientes comprem "fiado" e efetuem o pagamento no final do mês. No entanto, a organização e integridade das anotações podem ser comprometidas à medida que o número de clientes e o volume de transações aumentam. Em suma esse é um sistema simples, feito afim de substituir as cadernetas de papel. No momento o desenvolvimento é 100% autoral, ainda não tem contribuições de terceiros.

## Layout da tela  principal da aplicação
![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/a2787568-4c06-4f34-b840-90f9611f3660)

## Layout da tela de cadastro de clientes
![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/2f9a9687-4347-475a-9ec0-480edb43c375)
![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/78b413a5-18cc-46ca-9d2a-d5ed88653e76)


## Layout da tela de listar todos os clientes (somente informações importantes)
![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/b7266516-f1ca-4b4a-b9df-4c794cd53347)
<br>
## Detalhamento do cliente (todas as informações)
![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/50f18fd6-c19c-446e-ad6d-0219d5d513a3)

## Modelo conceitual
em breve...

## Segurança com Spring-security (JWT)
![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/0e561a26-74c9-4981-99bd-19e577f0bcf4)

![image](https://github.com/im2back/desafio-pessoal-mercearia/assets/117541466/81176b92-603c-4edf-8f33-b3df059c5d4e)

## Futuras implementações (Atualização de 21/11/2023)


( 1 ) ------> Implementar o Spring-Security JWT
Obs : O security foi implementado porém o Token está sendo enviado como parametro. O token deve ser enviado por medio de um cabeçalho.


( 2 ) -------> Ajustar os retornos quando o  usuario não tem autorização para acessar determinados URL's


( 3 ) -------> Implementar Deleção de cliente. (Cliente+historico de produtos)


( 4 ) -------> Acrecentar um capo de observação ao realizar uma compra.


( 5 ) -------> Acrecentar telefone no cadastro de cliente


( 6 ) -------> Implementar funcionalidade que envia E-MAIL ou SMS todo inicio de mês caso o cliente não tenha pago.


( 7 ) -------> Acrecentar um Status da conta (PAGO || EM ABERTO)


( 8 ) -------> Adicionar feedback de compra realizada com sucesso
 

( 9 ) -------> Na pagina onde listamos todos os cliente, implementar uma fução que exiba o total(soma de todos) em aberto de todos os clientes


( 10 ) -------> Corrigir retorno de senha invalida ao entrar


( 11 ) -------> Adicionar campo de pesquisa por nome do cliente ,na pagina onde listamos todos os clientes

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
- Segurança da API : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/SpringSecurity-white.svg?style=flat&logo=springsecurity&logoColor=green" target="_blank"></a>




# Como executar o projeto

## Back end
Pré-requisitos: Java 17

```bash
# clonar repositório
# entrar na pasta do projeto back end
cd backend
# executar o projeto
./mvnw spring-boot:run
```

# Autor

Jefferson Richards Sena de souza
https://www.linkedin.com/in/jefferson-richards-sena-de-souza-4110a3222/
