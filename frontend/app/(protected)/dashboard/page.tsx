import { getCurrentUser } from "@/lib/auth/getCurrentUser";
import { backendUrl } from "@/utils/constants";

export const dynamic = "force-dynamic";

export default async function Dashboard() {
  const { user: me } = await getCurrentUser();
  if (!me) return <p className="p-6">Not authenticated.</p>;

  let orgName = "Unknown Organization";
  if (me.organizationId) {
    try {
      const res = await fetch(
        `${backendUrl}/api/organizations/${me.organizationId}`,
        { cache: "no-store" }
      );
      if (res.ok) {
        const org = await res.json();
        orgName = org.name;
      }
    } catch {
      /* ignore */
    }
  }

  return (
    <div className="min-h-screen bg-background p-6">
      <div className="max-w-4xl mx-auto">
        <div className="rounded-lg bg-card p-6 shadow">
          <h1 className="text-3xl font-bold text-card-foreground mb-4">
            Welcome, {me.name || me.email}!
          </h1>
          <div className="space-y-2 text-muted-foreground">
            <p>
              <span className="font-medium text-card-foreground">Email:</span>{" "}
              {me.email}
            </p>
            <p>
              <span className="font-medium text-card-foreground">
                Organization:
              </span>{" "}
              {orgName}
            </p>
            <p>
              <span className="font-medium text-card-foreground">Role:</span>{" "}
              {me.role || "No role assigned"}
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}
