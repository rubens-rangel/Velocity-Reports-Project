# Projeto de Aprendizado: Relatórios com Apache Velocity

Este projeto tem como objetivo demonstrar e fornecer um exemplo prático de como gerar relatórios dinâmicos utilizando o Apache Velocity como motor de templates. Através de uma API simples, dados em formato JSON podem ser enviados para gerar relatórios personalizados em diversos formatos (atualmente focado no exemplo JSON).

## Exemplo de Requisição (curl)

A seguinte requisição `curl` ilustra como interagir com a API para gerar um relatório financeiro:

```bash
curl --location 'http://localhost:8080/api/report/financial-report' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Relatório Completo",
    "startDate": "2023-01-01",
    "endDate": "2023-03-31",
    "summary": {
        "totalSales": 150000.75,
        "totalExpenses": 75000.50,
        "netProfit": 75000.25
    },
    "salesByCategory": [
        {
            "name": "Eletrônicos",
            "quantity": 120,
            "totalValue": 80000.50,
            "percentage": 0.5333
        }
    ],
    "topClients": [
        {
            "name": "Cliente Ouro",
            "totalValue": 25000.00,
            "lastPurchase": "2023-03-15",
            "loyaltyLevel": "Ouro"
        }
    ]
}'
