"""seed data

Revision ID: 9ae8753d4078
Revises: a96311092586
Create Date: 2025-09-28 13:21:54.669847

"""
from typing import Sequence, Union

from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision: str = '9ae8753d4078'
down_revision: Union[str, Sequence[str], None] = 'a96311092586'
branch_labels: Union[str, Sequence[str], None] = None
depends_on: Union[str, Sequence[str], None] = None


def upgrade() -> None:
    """Upgrade schema."""
    for i in range(1234):
        op.execute("INSERT INTO test_schema.test_table (enum) VALUES ('Potato')")
        op.execute("INSERT INTO test_schema.test_table (enum) VALUES ('potato')")
        op.execute("INSERT INTO test_schema.test_table (enum) VALUES ('Tomato')")
        op.execute("INSERT INTO test_schema.test_table (enum) VALUES ('tomato')")


def downgrade() -> None:
    """Downgrade schema."""
    pass
