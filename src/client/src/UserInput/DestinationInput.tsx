import * as React from 'react';
import { Button }  from 'react-bootstrap';

class DestinationInput extends React.Component<{}, { userInput: string }>  {
    constructor(props: any) {
        super(props);

        this.state = {
            userInput: ''
        };
        this.handleChange = this.handleChange.bind(this);
        this.addLocationToRepo = this.addLocationToRepo.bind(this);
    }

    componentDidMount() {
        this.setState(
            {
                userInput : ''
            });
    }

    addLocationToRepo() {
        if (this.state.userInput !== '') {
            fetch('http://localhost:8080/rest/destination/submit-destination', {
                method: 'POST',
                body: JSON.stringify(this.state.userInput)
            })
                .then(response => response.json());
        }
    }

    handleChange(event: any) {
        this.setState({userInput: event.target.value});
    }

    render() {
        return (
            <div>
                <form>
                    New Location:<br/>
                    <input
                        type="location"
                        value={this.state.userInput}
                        onChange={this.handleChange}
                    /><br/>
                </form><br/>
                <Button onClick={this.addLocationToRepo}>Submit Destination</Button>
            </div>
        );
    }
}

export default DestinationInput;