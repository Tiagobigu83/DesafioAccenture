#language: pt

Funcionalidade: Abrir nova janela e validar mensagem
Como um usuário do site DemoQA
Quero abrir uma nova janela
Para validar se a mensagem correta é exibida

Cenário: Validar abertura de nova janela e mensagem correta
Dado que estou na página inicial
Quando eu clicar no menu Alerts, Frame & Windows
E eu clicar no submenu Browser Windows
E eu clicar no botão New Window
Então uma nova janela deve ser aberta
E eu devo validar que a mensagem This is a sample page está presente
E eu fecho a nova janela