# language: pt

Funcionalidade: Testar o comportamento da Progress Bar no site DemoQA

Como um usuário
Quero validar o comportamento da barra de progresso
Para garantir que ela funcione conforme o esperado

Cenário: Parar a Progress Bar antes dos 25% e verificar se atinge os 100%
Dado  que inicio a página do DemoQA
Quando eu clicar no menu Widgets
E eu clicar no submenu Progress Bar
E eu clicar no botão Start
E eu parar a Progress Bar antes de atingir 25%
Então o valor exibido da Progress Bar deve ser menor ou igual a 25%
E eu volto a apertar no botão Start
E eu aguardo a Progress Bar atingir 100%
Então o valor exibido da Progress Bar deve ser igual a 100%
