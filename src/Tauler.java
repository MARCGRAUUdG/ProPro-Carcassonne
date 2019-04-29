
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Tauler {
    public List<Posicio> getPosDisponibles(Fitxa f) {
        List<Posicio> l = new List<Posicio>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Posicio> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Posicio posicio) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Posicio> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Posicio> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Posicio get(int index) {
                return null;
            }

            @Override
            public Posicio set(int index, Posicio element) {
                return null;
            }

            @Override
            public void add(int index, Posicio element) {

            }

            @Override
            public Posicio remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Posicio> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Posicio> listIterator(int index) {
                return null;
            }

            @Override
            public List<Posicio> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        return l;
    }

    public boolean tencaRegions(Fitxa f) {
        return false;
    }

    public void actualitzarPunts(Fitxa f) {
    }
}
