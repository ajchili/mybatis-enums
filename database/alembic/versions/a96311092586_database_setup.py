"""database setup

Revision ID: a96311092586
Revises: 
Create Date: 2025-09-28 13:05:12.341795

"""
from typing import Sequence, Union

from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision: str = 'a96311092586'
down_revision: Union[str, Sequence[str], None] = None
branch_labels: Union[str, Sequence[str], None] = None
depends_on: Union[str, Sequence[str], None] = None


def upgrade() -> None:
    """Upgrade schema."""
    op.execute("CREATE SCHEMA IF NOT EXISTS test_schema")
    op.execute(
        "CREATE TYPE test_schema.test_enum AS ENUM ('Potato', 'potato', 'Tomato', 'tomato')")
    op.execute(
        "CREATE TABLE test_schema.test_table (id SERIAL PRIMARY KEY, enum test_schema.test_enum)")


def downgrade() -> None:
    """Downgrade schema."""
    pass
