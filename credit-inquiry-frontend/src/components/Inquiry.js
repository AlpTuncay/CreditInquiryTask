import React from "react";

const axios = require("axios").default;

class Inquiry extends React.Component {
    constructor(props){
        super(props);

        this.state = {};
    };

    formInputChangeHandler = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    };

    sendInquiryRequest = (event) => {
        event.preventDefault();
        
        axios.post("http://127.0.0.1:8000/inquiry", this.state).then(response => {
            console.log(response);
            this.setState({
                result: response.data
            });
            console.log(this.state.result);
        }).catch(error => {
            console.error(error);
            this.setState({
                result: error.response.data
            })
        })

    };

    render() {    

        const showInquiryResult = () => {
            if(Boolean(this.state.result)){
                const {inquirerName, inquirerLastName, inquiryResult, creditLimit} = this.state.result;
                if(inquiryResult == "Onay"){
                    return (
                        <div className="card">
                            <div className="card-body">
                                Sayın {inquirerName} {inquirerLastName}, {creditLimit} TL değerindeki kredi başvurunuz onaylanmıştır.
                            </div>
                        </div>     
                    )
                } else if(inquiryResult == "Ret") {
                    return (
                        <div className="card">
                            <div className="card-body">
                                Sayın {inquirerName} {inquirerLastName}, kredi başvurunuz reddedilmiştir.
                            </div>
                        </div>     
                    )
                } else {
                    return (
                        <div className="card">
                            <div className="card-body">
                                {this.state.result}
                            </div>
                        </div>     
                    )
                }
    
            }
        };
    
        return (
            <div className="row d-flex justify-content-center">
                <div className="col-md-5">
                    <div className="card">
                        <div className="card-body">
                            <h3>Kredi Sorgulama</h3>
                        </div>
                    </div>
                    <br />
                    <div className="card">
                        <div className="card-body">
                            <form className="form-horizontal" content="application/json" onSubmit={this.sendInquiryRequest}>
                                <div className="form-group">
                                    <label htmlFor="name" className="control-label">Ad:</label>
                                    <div className="col-md-12">
                                        <input id="name" className="form-control" name="inquirerName" type="text" placeholder="Ad" onChange={this.formInputChangeHandler} required/>
                                    </div>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="surname" className="control-label">Soyad:</label>
                                    <div className="col-md-12">
                                        <input id="surname" className="form-control" name="inquirerLastName" type="text" placeholder="Soyad" onChange={this.formInputChangeHandler} required/>
                                    </div>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="tc_no" className="control-label">TC Kimlik Numarası:</label>
                                    <div className="col-md-12">
                                        <input id="tc_no" className="form-control" name="inquirerId" type="text" placeholder="300xxxxxxxx" pattern="[0-9]{11}" onChange={this.formInputChangeHandler} required/>
                                    </div>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="phone_num" className="control-label">Telefon Numarası:</label>
                                    <div className="col-md-12">
                                        <input id="phone_num" className="form-control" name="inquirerPhoneNumber" type="text" pattern="[0-9]{10}" placeholder="53xxxxxxxx" onChange={this.formInputChangeHandler} required/>
                                    </div>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="income" className="control-label">Aylık Geliriniz:</label>
                                    <div className="col-md-12">
                                        <input id="income" className="form-control" name="inquirerIncome" type="text" onKeyPress={this.validate} placeholder="1000" onChange={this.formInputChangeHandler} required/>
                                    </div>
                                </div>
                                <div className="form-group">
                                    <button className="btn btn-default btn-success" type="submit">Kredi Sorgula</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <br />
                    {showInquiryResult()}
                </div>
            </div>
        );
    }

}

export default Inquiry;