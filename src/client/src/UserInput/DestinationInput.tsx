import * as React from 'react';
import { Button }  from 'react-bootstrap';

class DestinationInput extends React.Component<{}, { userInput: string }>  {
    constructor(props: any) {
        super(props);

        this.state = {
            userInput: ''
        };
    }

    componentDidMount() {
        this.setState(
            {
                userInput : ''
            });
    }

    addLocationToRepo() {
        if (this.state.userInput != null || '') {
            fetch('http://localhost:8080/submit-destination', {
                body: JSON.stringify( this.state.userInput )
            })
                .then(response => response.json());
        }
    }

    render() {
        return (
            <div>
                <form>
                    New Location:<br/>
                    <input type="text" value={this.state.userInput} name="location"/><br/>
                </form><br/>
                <Button onClick={this.addLocationToRepo()}>Something</Button>
            </div>
        );
    }
}

export default DestinationInput;