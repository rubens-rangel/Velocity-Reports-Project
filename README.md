##########Projeto de aprendizado de relatorios feitos no Apache Velocity.############



################ Exemplo de Requisição ################
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
