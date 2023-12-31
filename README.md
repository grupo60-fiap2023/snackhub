<center>
  <p align="center">
    <img src="readmefiles/logoclean.png" width="150">
    <img src="https://icon-library.com/images/java-icon-png/java-icon-png-15.jpg"  width="150" />
  </p>  
  <h1 align="center">Clean Architecture</h1>
  <br align="center">
    Este projeto tem a finalidade educacional, como parte do Tech Challenge do Curso de Arquitetura de Software.
</center>

# Conceito

## O que é Clean Architecture?

Clean Architecture, também conhecida como Arquitetura Limpa, é uma abordagem de desenvolvimento de software proposta por Robert C. Martin, um renomado engenheiro de software, autor e consultor. A principal ideia por trás da Clean Architecture é criar sistemas que sejam independentes de frameworks, banco de dados e detalhes de interface do usuário, enfatizando a separação de preocupações e a clareza na organização do código.

O objetivo da Clean Architecture é desenvolver sistemas altamente sustentáveis, testáveis e escaláveis, facilitando a manutenção contínua ao longo do tempo, permitindo a troca de componentes sem alterar a lógica central e tornando o código mais compreensível para novos desenvolvedores que trabalham no projeto.

Essa arquitetura promove a separação de preocupações e permite que cada camada se concentre em sua responsabilidade específica. A ideia é que as dependências fluam de dentro para fora, ou seja, as camadas internas não devem depender das camadas externas, tornando o sistema mais modular e independente.


![img.png](readmefiles/cleanmodel.png)

## Decisão Arquitetural do Desafio

O projeto SnackHub possui três principais módulos:
* Domain
* Application
* Infrastructure

## Domain

Nesse módulo estão presente as nossas classes de dominio, projetadas com a utilização de práticas do DDD. 
No cenário do Clean Architecture representam as Entities. 

As classes presente nesse módulo não possuem nenhuma dependência externa ou de framework.

## Application

A camada de Use Case é responsável por implementar os casos de uso específicos do negócio da aplicação. 
A caracteristica desse módulo é abstrair de regras de negócio: A camada de Use Case contém a lógica do negócio da aplicação, mas sem detalhes de implementação relacionados a infraestrutura ou apresentação.

## Infrastructure

A camada de infraestrutura é uma das camadas principais do Clean Architecture (Arquitetura Limpa) proposta por Robert C. Martin. 
Essa camada é responsável por lidar com os detalhes técnicos, como o acesso a bancos de dados, serviços externos, sistemas de arquivos e outras tecnologias que não são específicas do domínio da aplicação. Sua principal função é permitir a comunicação entre a aplicação e o mundo externo, mantendo a lógica de negócio isolada e independente de detalhes de implementação.

## Decisão Orquestração de Cointainers do Desafio

Para a orquestração dos containers foi escolhido o Kubernetes para garantir escalabilidade, alta disponibilidade e facilidade de gerenciamento. 
Com o Kubernetes, podemos orquestrar e automatizar o deployment, scaling e atualizações de nossos containers de forma eficiente. 
Além disso, sua capacidade de autorrecuperação assegura a estabilidade da aplicação, reduzindo o tempo de inatividade.

## Organização Kubernetes

No diretório "Kubernetes" localizado na raiz do projeto, encontram-se as configurações dos deployments, volumes e services.

* O deployment da aplicação e da base de dados está configurado para 1 réplica, como pode ser visto nos deployments. A aplicação pode ser escalada para mais de um Pod, conforme solicitado no desafio. A recomendação do Kubernetes é que a escala para LoadBalance seja de 3 a 5 Pods.
* O services foram configurados como Load Balance e do tipo NLB.
* Os dados sensíveis estão cadastrados no secrets e ofuscados.

## APIs solicitadas na fase 2

* Checkout do Pedido, que deverá receber os produtos solicitados e retornar a identificação do pedido.

A api `orders/createOrder` é solicitado o identificador do cliente e uma coleção de itens com o identificador do produto e a quantidade.
Como retorno da API será listado o que o pedido contém e o identificador do pedido com o status do pagamento e do pedido.

* Consultar status de pagamento do pedido, que informa se o pagamento foi aprovado ou não.

A api `/orders/paymentstatus/{id}` quando informado o id do pedido retornará o status do pagamento.

* A lista de pedidos deverá retornar os pedidos com suas descrições, ordenados por recebimento e por status com a seguinte prioridade: Pronto > Em Preparação > Recebido; Pedidos com status Finalizado não devem aparecer na lista.

A api `/orders/list` retornará os pedidos com a prioridade solicitada.

* Atualizar o status do pedido

A api `/orders/{id}` atualiza um determinado pedido com o status informado.

* Webhook

Descrito na sessão subsequente como utilizar. API Paymente QR Code é utilizada para o pagamento. 

Como melhoria nessa fase, foi acatada a sugestão de trocas de UUID das entidades por Id sequences.

# Vamos Executar?

## Ferramentas necessárias / Pré-requisitos

- JDK 17
- IDE de sua preferência
- Kubernets / kubctl
- Docker
- O banco de dados utilizado é o MySQL, caso queira ver os dados é sugerido a utilização do SGBD mySQL Workbench

## Como executar com Kubernetes?

**1. Clonar o repositório:**
```sh
git clone https://github.com/grupo60-fiap2023/snackhub
```

**2. Acessar a pasta Kubernetes:**
```sh
cd Kubernetes
```

**3. Executado a criação do banco MySQL:**
```bat
'.\Init DB.bat'
```

Caso esteja utilizado um sistema operacional que não seja Windows:
```sh
kubectl delete service,deployments,persistentvolumeclaim,configmap,secrets --all
kubectl apply -f Secrets\secrets.yml
kubectl apply -f PersistentVolumeClaim\pvc-db.yml
kubectl apply -f ConfigMap\configmap-db.yml
kubectl apply -f Deployments\deployment-db.yml
kubectl apply -f Services\svc-db.yml
```
*Atenção: Na primeira linha do comando tem um delete all para remover possiveis lixos em caso de atualização das configurações.

**4. Executado a criação do banco Aplicação:**
```bat
'.\Init App.bat'
```

Caso esteja utilizado um sistema operacional que não seja Windows:
```sh
kubectl delete Services app-svc-lb
kubectl delete Deployments aplicacao-deployment

kubectl apply -f Deployments\deployment-app.yml
kubectl apply -f Services\svc-app.yml
```

**Os pods serão inicializados e a aplicação estará disponível em: http://localhost:8080/swagger-ui/index.html**

![img.png](readmefiles/k8s_desktop.png)


## Como executar só com Docker?

**1. Subir a aplicação e o banco de dados MySQL com Docker:**
```shell
docker-compose up -d
```

**2. Após a execução do comando acima será baixado as imagens do MySQL e da Aplicação presente no DockerHub e os containers serão iniciados.**
```
[+] Running 3/3
 ✔ Network snackhub_network     Created                                                                                                                                                                                                                                                                                                                                                                                          0.7s 
 ✔ Container snackhub-mysql-db  Started                                                                                                                                                                                                                                                                                                                                                                                          1.9s 
 ✔ Container snackhub-app       Started 
```
Pronto! Aguarde que em instantes o MySQL irá estar pronto para ser consumido
na porta 3307. Por conflito de porta com a 3306 foi escolhido a local 3307.

Ver imagem abaixo a organização no Docker Desktop:

**- Containers**

![img.png](readmefiles/docker1.png)

**- Images**

![img_1.png](readmefiles/docker2.png)

O MySQL já estará disponível:

![img_2.png](readmefiles/mysql.png)

**4. No diretório `src/main/resources/db.migration` está disponível as DDLs a serem executadas com a finalidade de criação das tabelas. O Docker compose inicia a base de dados.**

**5. Como a aplicação também foi inicializada a mesma possui uma interface Swagger, disponível em: http://localhost:8080/swagger-ui/index.html**

Além das funcionalidades da Fase 1, foram incluídas:
* Checkout de Pedido, que deverá receber os produtos solicitados e o cliente e retornar a identificação do pedido. (Como sugestão dos professores foi alterado o id de UUID para Number);
* Consultar status de pagamento do pedido, que informa se o pagamento foi aprovado ou não;
* Lista de pedidos ordenado por recebimento e por status;
* Atualizar o status do pedido;
* Integração com o Mercado Pago para gerar o QR Code de Pagamento;
* Webhook de criação de pagamento;

Todas essas features novas podem ser vistas abaixo:

![img.png](readmefiles/swagger.png)


## Sou Desenvolvedor, tem informações a mais?

## Docker

`docker-compose.yml`: YAML (Yet Another Markup Language) que é usado para definir, configurar e executar aplicativos multi-container usando o Docker. Em nosso arquivo configuramos 2 serviços,
um para o base de dados e outro para a aplicação. Foi adicionado uma configuração extra, pois as vezes a aplicação subia antes da base de dados, com isso foi configurado alguns Waits.
Nesse mesmo arquivo possui a configuração de rede do docker e os mapeamos de portas dos serviços.

`Dockerfile`: Dockerfile apontando para um image do `eclipse-temurin:17.0.5_8-jre-alpine`. A imagem Docker do Eclipse Temurin permite que você execute aplicativos Java em um ambiente isolado e portátil.
Nesse caso, "17.0.5_8" indica a versão do Java 17.0.5, enquanto "jre" significa que a imagem contém apenas a Java Runtime Environment, que é o ambiente de execução do Java, sem o kit de desenvolvimento (JDK). Por fim, "alpine" refere-se à base da imagem, que é a distribuição leve Alpine Linux
Nesse mesmo arquivo copiamos o Jar gerado pela aplicação, criamos um Usuário e Grupo e executamos o Jar da aplicação.


`Dockerfile.dev`: Muito similar com a configuração acima, a diferença que esse Dockerfile tem o comando para atualizar e regerar o Jar da aplicação.

### Comandos úteis do Docker


Gerar a Imagem da Aplicação atraves do DockerFile
```shell
docker build -t grupo60fiap2023/snackhub-app .
```

Para subir a imagem para o Hub:
```shell
docker push grupo60fiap2023/snackhub-app
```

## Quero desenvolver novos Use Cases, qual a forma mais rápida de testar?

1. Caso o desenvolvedor não queira executar a geração do .Jar e levantar o Docker, sugiro executar o docker-compose-only-mysql. 
Esse YML contêm apenas o serviço de base de dados e a aplicação poderá ser executada conforme o próximo passo.

```shell
docker-compose -f docker-compose-only-mysql.yml up
```

2. É possível executar como uma aplicação Java através do método main() na classe Main.java

Obs. Com essa abordagem o desenvolvimento se torna mais rápido, pois não teremos que regerar Jar e atualizar no Docker, mas os testes tem
que ser no Docker, pois reflete o ambiente do "Cliente".

## Configuração da Aplicação

Nos arquivos `src/main/resources/application.yml` e `src/main/resources/application-development.yml` temos as configurações da aplicação, ou seja:
- Configuração do datasource
- Configuração do JPA
- Configurações do Spring Boot
- Dados de Acesso a base de dados

## Testando notificações com Webhook.site

     - Abra o https://webhook.site
  - Copie a URL para receber as notificações de webhook teste:

    ![img.png](readmefiles/webhooksite.png)

- ### Request de integração com o Mercado Pago para a criação do QR Code:
      - Adicione o token: TEST-8612056198451486-073114-a61ef52c83bb32844fd839b3d311672c-187206752

  - Cole a url gerada do webhook.site no campo notification_url do seu body
  
    ![img.png](readmefiles/create-qrdata.png)
  
  - Copie o qr_data gerado:
  
    ![img.png](readmefiles/generate-qrdata.png)
  
  - Já é possível validar a notificação de retorno do Mercado Pago com um número de ordem criado:
  
    ![img.png](readmefiles/merchant-order.png)
  
- ### Request de criação da imagem do QR Code:
    - Cole o qr_data gerado na request anterior
  
      ![img.png](readmefiles/create-image-qrcode.png)
  
    - Scnnear o QR e realize o pagamento:
  
      ![img.png](readmefiles/image-genarate.png)
  
  - ### Pagamento com o Mercado Pago:
    - QR scanneado com app do Mercado Pago:
    
      ![img.png](readmefiles/payment.png)
    
    - Erro ao realizar o pagamento:
    
      ![img.png](readmefiles/error-payment.png)
    
    - É possível validar a notificação da criação do pagamento no Webhook teste:
    
      ![img.png](readmefiles/payment-created.png)


# Fase 3 - Deploy App AWS - CI/CD com Action
### Vídeo para avaliação dos mentores
https://youtu.be/zUBYuLAImTA

Configuração CI/CD:
* Build do código Java
* Análise de código com SonarCloud
* Configuração das credenciais Amazon
* Criação/Atualização da App

Arquivo build.yml:
https://github.com/grupo60-fiap2023/snackhub/blob/main/.github/workflows/build.yml

PR da Exibição: https://github.com/grupo60-fiap2023/snackhub/actions/runs/6806916290

SonarCloud do projeto: https://sonarcloud.io/summary/overall?id=grupo60-fiap2023_snackhub