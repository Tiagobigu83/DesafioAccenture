# language: pt

Funcionalidade: Gerenciar registros de Web Tables
  Como um usuário do site DemoQA
  Quero gerenciar registros na tabela da Web
  Para criar, editar e deletar registros

  Cenário: Criar, editar e deletar um registro
    Dado que inicio a página inicial
    Quando eu clicar no menu Elements
    E clicar Web Tables no submenu
    E eu criar um novo registro com os dados "João", "Silva", "joao.silva@teste.com", "25", "4000", "Desenvolvimento"
    E eu editar o registro 0 com os dados "João", "Souza", "joao.souza@teste.com", "26", "4500", "Design"
    E eu deletar o registro 0
    Então eu fecho o navegador

  Cenário: Criar e deletar novos registros
    Dado que inicio a página inicial
    Quando eu clicar no menu Elements
    E clicar Web Tables no submenu
    E eu criar 12 novos registros
    E eu deletar todos os registros
    Então eu fecho o navegador
