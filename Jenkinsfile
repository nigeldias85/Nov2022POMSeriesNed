pipeline {
    
    agent any
    
    stages {
        
        stage("Build") {
            steps {
                echo("Build the project")
            }
        }
        
        stage("Deploy to dev") {
            steps {
                echo("Deploying to dev environment")
            }
        }
        
        
        stage("Deploy to qa") {
            steps {
                echo("Deploying to qa environment")
            }
        }
        
        
        stage("Run regression automation test cases") {
            steps {
                echo("Run regression automation test cases")
            }
        }
        
        
        stage("Deploy to stage") {
            steps {
                echo("Deploying to stage environment")
            }
        }
        
        
        stage("Run sanity automation test cases") {
            steps {
                echo("Run sanity automation test cases")
            }
        }
        
        
        stage("Deploy to prod") {
            steps {
                echo("Deploy to prod")
            }
        }
    }
    
    
}