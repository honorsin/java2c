
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class NfaIntepretor {

  public Nfa start;
  public Input input;

	public boolean debug = true;

  public NfaIntepretor(Nfa start, Input input) {
    this.start = start;
    this.input = input;
  }


  public Set<Nfa> e_closure(Set<Nfa> input) {
    System.out.print("ε-Closure( " + strFromNfaSet(input) + " ) = ");

    Stack<Nfa> nfaStack = new Stack<Nfa>();
    if (input == null || input.isEmpty()) {
      return null;
    }

    // set中元素都压入栈中
    Iterator<Nfa> it = input.iterator();
    while (it.hasNext()) {
      nfaStack.add(it.next());
    }

    while(nfaStack.empty() == false) {
      Nfa p = nfaStack.pop();

      if (p.next != null && p.getEdge() == Nfa.EPSILON) {
        if (input.contains(p.next) == false) {
          nfaStack.push(p.next);
          input.add(p.next);
        }
      }

      if (p.next2 != null && p.getEdge() == Nfa.EPSILON) {
        if (input.contains(p.next2) == false) {
          nfaStack.push(p.next2);
          input.add(p.next2);
        }
      }
    }

    if (input != null) {
      System.out.println("{ " + strFromNfaSet(input) + " }");
    }
    
    
    return input;    
  }

  private String strFromNfaSet(Set<Nfa> input) {
    String s = "";
    Iterator it = input.iterator();

    while(it.hasNext()) {
      s += ((Nfa)it.next()).getStateNum();
      if (it.hasNext()) {
        s += ",";
      }
    }
    return s;
  }
  public Set<Nfa> move(Set<Nfa> input, char c) {
    Set<Nfa> outSet = new HashSet<Nfa>();
    Iterator it = input.iterator();

    while(it.hasNext()) {
      Nfa p = (Nfa)it.next();

      Byte cb = (byte)c;
      
      if (p.getEdge() == c || (p.getEdge() == Nfa.CCL && p.inputSet.contains(cb))) {
        outSet.add(p.next);
      }
    }

    if (outSet != null && debug) {
      System.out.print("move({ " + strFromNfaSet(input) + " }, '" + c + "')= ");
      System.out.println("{ " + strFromNfaSet(outSet) + " }");
    }

    return outSet;
  }
  private boolean hasAcceptState(Set<Nfa> input) {
    boolean isAccepted = false;
    if (input == null || input.isEmpty()) {
      return false;
    }

    String acceptedStatement = "Accept State: ";
    Iterator it = input.iterator();

    while (it.hasNext()) {
      Nfa p = (Nfa) it.next();
      if (p.next == null && p.next2 == null) {
        isAccepted = true;
        acceptedStatement += p.getStateNum() + " ";
      }
    }

    if (isAccepted) {
      System.out.println(acceptedStatement);
    }

    return isAccepted;
  }

  public void intepretNfa() {
    System.out.println("Input string: ");
    input.ii_newFile(null);
    input.ii_advance();
    input.ii_pushback(1);

    Set<Nfa> next = new HashSet<Nfa>();
    next.add(start);
    e_closure(next);

    Set<Nfa> current = null;
    char c;
    String inputStr = "";
    boolean lastAccepted = false;

    while ((c = (char) input.ii_advance()) != input.EOF) {
      current = move(next, c);
      next = e_closure(current);

      if (next != null) {
        if (hasAcceptState(next)) {
          lastAccepted = true;
        }
      }
      else {
        break;
      }

      inputStr += c;
    }

    if (lastAccepted) {
      System.out.println("The Nfa Machine can recognize string: " + inputStr);
    }
  }

}
