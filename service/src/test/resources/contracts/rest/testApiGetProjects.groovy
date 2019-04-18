org.springframework.cloud.contract.spec.Contract.make {
    description("""
    test Project api
""")
    request {
        method 'GET'
        url '/api/projects'
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