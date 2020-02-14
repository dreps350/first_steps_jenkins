#!groovy

properties([disableConcurrentBuilds()])

node('master') {
    echo "reparation stage"
}

def branches = [:]

for ( int i = 0; i < 3; i++ ) {
    def branchName = "Branch ${i}"

    branches[branchName] = {
        node('master') {
            stage(branchName) {
                echo "Running branch #${i}"
            }
        }
    }
}

// Give the branches to Jenkins for parallel execution:
parallel branches

node('master') {
    echo "final stage"
}