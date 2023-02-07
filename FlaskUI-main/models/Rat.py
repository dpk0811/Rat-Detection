from .db import db


class Rat(db.Model):
    id = db.Column(db.Integer, primary_key=True) # primary keys are required by SQLAlchemy
    training_data = db.Column(db.String(100), unique=True, nullable=False)
    coordinates = db.Column(db.String(1000), nullable=False)
    match_percentile = db.Column(db.Float(), nullable=False)
