org.springframework.cloud.contract.spec.Contract.make {
    description("""
    test Task api
""")
    request {
        method 'GET'
        url '/api/tasks'
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
    }
}