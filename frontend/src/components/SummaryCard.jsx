import "./SummaryCard.css";

function SummaryCard({title, value, description}){
    return (
        <div className="summary-card">
            <div className="summary-card-info">
                <p>{title}</p>
                <h3>{value}</h3>
                <span>{description}</span>
            </div>
        </div>
    );
}

export default SummaryCard;