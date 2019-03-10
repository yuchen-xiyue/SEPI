package zuma;

import java.util.Vector;

public interface IMaze {
	public INode getOrigin();

	public INode getEnd();

	public int size();

	public Vector getLoaction(INode n);

	public Vector getDirection(INode n);

	public boolean hasBall(INode n);
}
